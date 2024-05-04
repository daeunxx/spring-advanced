package org.example.logtrace.trace.logtrace;

import org.example.logtrace.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class ThreadLocalLogTraceTest {

  ThreadLocalLogTrace threadLocalLogTrace = new ThreadLocalLogTrace();

  @Test
  void begin_end_level2() {
    TraceStatus status1 = threadLocalLogTrace.begin("hello1");
    TraceStatus status2 = threadLocalLogTrace.begin("hello2");
    threadLocalLogTrace.end(status2);
    threadLocalLogTrace.end(status1);
  }

  @Test
  void begin_exception_level2() {
    TraceStatus status1 = threadLocalLogTrace.begin("hello1");
    TraceStatus status2 = threadLocalLogTrace.begin("hello2");
    threadLocalLogTrace.exception(status2, new IllegalStateException());
    threadLocalLogTrace.exception(status1, new IllegalStateException());
  }
}