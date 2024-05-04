package org.example.logtrace.trace.logtrace;

import org.example.logtrace.trace.TraceStatus;

public interface LogTrace {

  TraceStatus begin(String message);

  void end(TraceStatus traceStatus);

  void exception(TraceStatus traceStatus, Exception e);
}
