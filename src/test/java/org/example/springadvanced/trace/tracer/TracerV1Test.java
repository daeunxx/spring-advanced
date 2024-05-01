package org.example.springadvanced.trace.tracer;

import static org.junit.jupiter.api.Assertions.*;

import org.example.springadvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class TracerV1Test {

  @Test
  void begin_end() {
    TracerV1 tracer = new TracerV1();
    TraceStatus traceStatus = tracer.begin("hi");
    tracer.end(traceStatus);
  }

  @Test
  void begin_exception() {
    TracerV1 tracer = new TracerV1();
    TraceStatus traceStatus = tracer.begin("hi");
    tracer.exception(traceStatus, new IllegalStateException());
  }
}