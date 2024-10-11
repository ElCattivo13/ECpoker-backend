package io.github.elcattivo13.ecpoker.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PokerController {

  private final PokerRegistry registry;

  PokerController(PokerRegistry registry) {
    this.registry = registry;
  }

  @PostMapping("/poker")
  CreatePokerResponse createPoker(@RequestBody PokerConfig pokerConfig) throws InvalidPokerConfigException {
    PokerSession newSession = new PokerSession(pokerConfig);
    registry.register(newSession);
    return new CreatePokerResponse(newSession);
  }
  
  @GetMapping("/poker/{sessonId}")
  PokerConfig getPoker(@PathVariable UUID sessionId) throws PokerNotFoundException {
    return registry.getSession(sessionId)
       .map(PokerSession::getConfig)
       .orElseThrow(() -> new PokerNotFoundException());
  }
  
  @PutMapping("/poker/{sessionId}/join")
  UUID joinGame(@RequestBody Player player, @PathVariable UUID sessionId) throws PokerNotFoundException {
    PokerSession session = registry.getSession(sessionId)
        .orElseThrow(() -> new PokerNotFoundException());
    if (session.getConfig().isNameNecessary() && StringUtils.isBlank(player.getName)) {
      throw new BadRequestException("Player name must not be null!");
    }
    UUID playerId = session.addPlayer(player.getName);
    // TODO Websocket message about players
    return playerId;
  }

  @PutMapping("/poker/{sessionId}/put-card/{playerId}/{card}")
  void putCard(@PathVariable UUID sessionId, @PathVariable UUID playerId, @PathVariable PokerCard card) throws PokerNotFoundException, PlayerNotFoundException, InvalidCardException {
    PokerSession session = registry.getSession(sessionId)
            .orElseThrow(() -> new PokerNotFoundException());
    if (!session.isKnownPlayer()) {
      throw new PlayerNotFoundException(;)
    }
    if (!session.getConfig().getAvailableCards().contains(card)) {
      throw new InvalidCardException();
    }
    
    //
  }

  @PutMapping("/poker/{sessionId}/reset/{adminKey}")
  void resetCards(@PathVariable UUID gameId, @PathVariable UUID adminKey) throws PokerNotFoundException, NotAllowedException {
    PokerSession session = registry.getSession(sessionId)
            .orElseThrow(() -> new PokerNotFoundException());
    session.reset(adminKey);
    // Websocket message about played cards
  }
}