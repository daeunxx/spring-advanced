package org.example.logtrace.trace.mocktrace;

import org.example.logtrace.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class MockTraceV1Test {

  @Test
  void begin_end() {
    MockTraceV1 tracer = new MockTraceV1();
    TraceStatus traceStatus = tracer.begin("hi");
    tracer.end(traceStatus);
  }

  @Test
  void begin_exception() {
    MockTraceV1 tracer = new MockTraceV1();
    TraceStatus traceStatus = tracer.begin("hi");
    tracer.exception(traceStatus, new IllegalStateException());
  }
}