package io.github.elcattivo13.ecpoker.model;

import io.github.elcattivo13.ecpoker.exception.InvalidPokerConfigException;
import org.springframework.util.CollectionUtils;

import java.util.Set;

public class PokerConfig {
  
  private Set<PokerCard> availableCards;
  private boolean nameNecessary;

  public void check() throws InvalidPokerConfigException {
    if (CollectionUtils.isEmpty(availableCards)) {
      throw new InvalidPokerConfigException();
    }
  }

  public Set<PokerCard> getAvailableCards() {
    return this.availableCards;
  }
  
  public void setAvailableCards(Set<PokerCard> availableCards) {
    this.availableCards = availableCards;
  }
  
  public boolean isNameNecessary() {
    return this.nameNecessary;
  }
  
  public void setNameNecessary(boolean nameNecessary) {
    this.nameNecessary = nameNecessary;
  }
}