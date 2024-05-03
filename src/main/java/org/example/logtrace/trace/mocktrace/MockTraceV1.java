package org.example.logtrace.trace.mocktrace;

import lombok.extern.slf4j.Slf4j;
import org.example.logtrace.trace.TraceId;
import org.example.logtrace.trace.TraceStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MockTraceV1 {

  private static final String START_PREFIX = "-->";
  private static final String COMPLETE_PREFIX = "<--";
  private static final String EX_PREFIX = "<X-";

  public TraceStatus begin(String message) {
    TraceId traceId = new TraceId();
    Long startTimeMs = System.currentTimeMillis();

    //로그 출력
    log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
    return new TraceStatus(traceId, startTimeMs, message);
  }

  public void end(TraceStatus traceStatus) {
    complete(traceStatus, null);
  }

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
          addSpace(EX_PREFIX, traceId.getLevel()), traceStatus.getMessage(), resultTimeMs, e.toString());
    }
  }

  //level=0
  //level=1     |-->
  //level=2     |   |--->
  //level=2 ex  |   |<X-
  private static String addSpace(String prefix, int level) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < level; i++) {
      stringBuilder.append((i == level - 1) ? "|" + prefix : "|   ");
    }
    return stringBuilder.toString();
  }
}
