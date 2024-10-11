package io.github.elcattivo13.ecpoker.model;

import io.github.elcattivo13.ecpoker.exception.CardAlreadyPlayedException;
import io.github.elcattivo13.ecpoker.exception.InvalidCardException;
import io.github.elcattivo13.ecpoker.exception.NotAllowedException;
import io.github.elcattivo13.ecpoker.exception.PlayerUnknownException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

public class PokerSession {
  
  private final UUID sessionId;
  private final UUID adminKey;
  private final PokerConfig config;
  private final LocalDateTime createdAt;
  private Map<UUID, String> players;
  private Map<UUID, PokerCard> playedCards;
  
  public PokerSession(PokerConfig config) {
    this.sessionId = UUID.randomUUID();
    this.adminKey = UUID.randomUUID();
    this.config = config;
    this.createdAt = LocalDateTime.now();
    this.players = new HashMap<>();
    this.playedCards = new HashMap<>();
  }
  
  public UUID addPlayer(String name) {
    UUID playerId = UUID.randomUUID();
    this.players.put(playerId, name == null ? "" : name);
    return playerId;
  }
  
  public void removePlayer(UUID playerId) {
    this.players.remove(playerId);
  }
  
  public boolean isKnownPlayer(UUID playerId) {
    return this.players.containsKey(playerId);
  }
  
  public void putCard(UUID playerId, PokerCard card) throws PlayerUnknownException, InvalidCardException, CardAlreadyPlayedException {
    if (!this.players.containsKey(playerId)) {
      throw new PlayerUnknownException();
    }
    if (!this.config.getAvailableCards().contains(card)) {
      throw new InvalidCardException();
    }
    if (this.playedCards.containsKey(playerId)) {
      throw new CardAlreadyPlayedException();
    }
    this.playedCards.put(playerId, card);
  }
  
  public void reset(UUID adminKey) throws NotAllowedException {
    if (!this.adminKey.equals(adminKey)) {
      throw new NotAllowedException();
    }
    this.playedCards.clear();
  }
  
  public boolean isOld() {
    return LocalDateTime.now().minusHours(24).isAfter(this.createdAt);
  }
    
  public UUID getSessionId() {
    return this.sessionId;
  }
  
  public UUID getAdminKey() {
    return this.adminKey;
  }
  
  public PokerConfig getConfig() {
    return this.config;
  }
  
}