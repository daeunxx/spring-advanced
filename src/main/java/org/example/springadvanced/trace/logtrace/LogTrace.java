package org.example.springadvanced.trace.logtrace;

import org.example.springadvanced.trace.TraceStatus;

public interface LogTrace {

  TraceStatus begin(String message);

  void end(TraceStatus traceStatus);

  void exception(TraceStatus traceStatus, Exception e);
}
