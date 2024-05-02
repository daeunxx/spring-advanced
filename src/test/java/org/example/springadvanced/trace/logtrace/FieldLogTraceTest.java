package org.example.springadvanced.trace.logtrace;

import org.example.springadvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class FieldLogTraceTest {

  FieldLogTrace fieldLogTrace = new FieldLogTrace();

  @Test
  void begin_end_level2() {
    TraceStatus status1 = fieldLogTrace.begin("hello1");
    TraceStatus status2 = fieldLogTrace.begin("hello2");
    fieldLogTrace.end(status2);
    fieldLogTrace.end(status1);
  }

  @Test
  void begin_exception_level2() {
    TraceStatus status1 = fieldLogTrace.begin("hello1");
    TraceStatus status2 = fieldLogTrace.begin("hello2");
    fieldLogTrace.exception(status2, new IllegalStateException());
    fieldLogTrace.exception(status1, new IllegalStateException());
  }
}