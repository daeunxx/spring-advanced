package org.example.springadvanced.trace;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TraceId {
  private String id;
  private int level;

  public TraceId() {
    this.id = createId();
    this.level = 0;
  }

  private String createId() {
    return UUID.randomUUID().toString().substring(0, 8);
  }

  public TraceId creatPreviousId() {
    return new TraceId(id, level - 1);
  }

  public TraceId createNextId() {
    return new TraceId(id, level + 1);
  }

  public boolean isFirstLevel() {
    return level == 0;
  }
}
