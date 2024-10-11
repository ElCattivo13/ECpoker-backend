package io.github.elcattivo13.ecpoker.model;

import java.util.Set;

public class PokerConfig {
  
  private Set<PokerCard> availableCards;
  private boolean nameNecessary;
  
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