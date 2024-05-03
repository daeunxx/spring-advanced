package org.example.logtrace.trace.logtrace;

import lombok.extern.slf4j.Slf4j;
import org.example.logtrace.trace.TraceId;
import org.example.logtrace.trace.TraceStatus;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

  private static final String START_PREFIX = "-->";
  private static final String COMPLETE_PREFIX = "<--";
  private static final String EX_PREFIX = "<X-";

  //  private TraceId traceIdHolder;  //traceId 동기화, 동시성 이슈 발생
  private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

  @Override
  public TraceStatus begin(String message) {

    syncTraceId();
    
    TraceId traceId = traceIdHolder.get();
    Long startTimeMs = System.currentTimeMillis();

    //로그 출력
    log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
    return new TraceStatus(traceId, startTimeMs, message);
  }

  private void syncTraceId() {
    TraceId traceId = traceIdHolder.get();
    if (traceId == null) {
      traceIdHolder.set(new TraceId());
    } else {
      traceIdHolder.set(traceId.createNextId());
    }
  }

  @Override
  public void end(TraceStatus traceStatus) {
    complete(traceStatus, null);
  }

  @Override
  public void exception(TraceStatus traceStatus, Exception e) {
    complete(traceStatus, e);
  }

  private void complete(TraceStatus traceStatus, Exception e) {
    long resultTimeMs = System.currentTimeMillis() - traceStatus.getStartTimeMs();
    TraceId traceId = traceStatus.getTraceId();
    if (e == null) {
      log.info("[{}] {}{} time={}ms", traceId.getId(),
          addSpace(COMPLETE_PREFIX, traceId.getLevel()), traceStatus.getMessage(), resultTimeMs);
    } else {
      log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
          addSpace(EX_PREFIX, traceId.getLevel()), traceStatus.getMessage(), resultTimeMs,
          e.toString());
    }

    releaseTraceId();
  }

  private void releaseTraceId() {
    TraceId traceId = traceIdHolder.get();
    if (traceId.isFirstLevel()) {
      traceIdHolder.remove();
    } else {
      traceIdHolder.set(traceId.creatPreviousId());
    }
  }

  private static String addSpace(String prefix, int level) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < level; i++) {
      stringBuilder.append((i == level - 1) ? "|" + prefix : "|   ");
    }
    return stringBuilder.toString();
  }
}
