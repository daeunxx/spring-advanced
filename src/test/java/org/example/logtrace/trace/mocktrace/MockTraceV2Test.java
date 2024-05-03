package org.example.logtrace.trace.mocktrace;

import org.example.logtrace.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class MockTraceV2Test {

  @Test
  void begin_end() {
    MockTraceV2 tracer = new MockTraceV2();
    TraceStatus traceStatus1 = tracer.begin("hello1");
    TraceStatus traceStatus2 = tracer.beginSync(traceStatus1.getTraceId(), "hello2");
    tracer.end(traceStatus2);
    tracer.end(traceStatus1);
  }

  @Test
  void begin_exception() {
    MockTraceV2 tracer = new MockTraceV2();
    TraceStatus traceStatus1 = tracer.begin("hello1");
    TraceStatus traceStatus2 = tracer.beginSync(traceStatus1.getTraceId(), "hello2");
    tracer.exception(traceStatus2, new IllegalStateException());
    tracer.exception(traceStatus1, new IllegalStateException());
  }
}