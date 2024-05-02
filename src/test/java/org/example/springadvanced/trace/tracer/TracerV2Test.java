package org.example.springadvanced.trace.tracer;

import org.example.springadvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class TracerV2Test {

  @Test
  void begin_end() {
    TracerV2 tracer = new TracerV2();
    TraceStatus traceStatus1 = tracer.begin("hello1");
    TraceStatus traceStatus2 = tracer.beginSync(traceStatus1.getTraceId(), "hello2");
    tracer.end(traceStatus2);
    tracer.end(traceStatus1);
  }

  @Test
  void begin_exception() {
    TracerV2 tracer = new TracerV2();
    TraceStatus traceStatus1 = tracer.begin("hello1");
    TraceStatus traceStatus2 = tracer.beginSync(traceStatus1.getTraceId(), "hello2");
    tracer.exception(traceStatus2, new IllegalStateException());
    tracer.exception(traceStatus1, new IllegalStateException());
  }
}