package io.github.elcattivo13.ecpoker.rest;

import io.github.elcattivo13.ecpoker.exception.*;
import io.github.elcattivo13.ecpoker.model.*;
import io.micrometer.common.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
class PokerController {

  private final PokerRegistry registry;

  PokerController(PokerRegistry registry) {
    this.registry = registry;
  }

  @PostMapping(value = "/poker", consumes = {"application/json"}, produces = {"application/json"})
  CreatePokerResponse createPoker(@RequestBody PokerConfig pokerConfig) throws InvalidPokerConfigException {
    pokerConfig.check();
    PokerSession newSession = new PokerSession(pokerConfig);
    registry.register(newSession);
    return new CreatePokerResponse(newSession);
  }
  
  @GetMapping(value = "/poker/{sessionId}", produces = {"application/json"})
  PokerConfig getPoker(@PathVariable UUID sessionId) throws PokerNotFoundException {
    return registry.getSession(sessionId)
       .map(PokerSession::getConfig)
       .orElseThrow(PokerNotFoundException::new);
  }
  
  @PostMapping(value = "/poker/{sessionId}/join", consumes = {"application/json"}, produces = {"text/plain"})
  String joinGame(@RequestBody Player player, @PathVariable UUID sessionId) throws NoPlayerNameException, PokerNotFoundException {
    PokerSession session = registry.getSession(sessionId)
        .orElseThrow(PokerNotFoundException::new);

    if (session.getConfig().isNameNecessary() && StringUtils.isBlank(player.getName())) {
      throw new NoPlayerNameException();
    }

    UUID playerId = session.addPlayer(player.getName());
    // TODO Websocket message about players
    return playerId.toString();
  }

  @PutMapping(value = "/poker/{sessionId}/put-card/{playerId}/{card}")
  void putCard(@PathVariable UUID sessionId, @PathVariable UUID playerId, @PathVariable PokerCard card) throws PokerNotFoundException, InvalidCardException, CardAlreadyPlayedException, PlayerUnknownException {
    PokerSession session = registry.getSession(sessionId)
            .orElseThrow(PokerNotFoundException::new);

    if (!session.isKnownPlayer(playerId)) {
      throw new PlayerUnknownException();
    }

    if (!session.getConfig().getAvailableCards().contains(card)) {
      throw new InvalidCardException();
    }
    
    session.putCard(playerId, card);
  }

  @PutMapping(value = "/poker/{sessionId}/reset/{adminKey}")
  void resetCards(@PathVariable UUID sessionId, @PathVariable UUID adminKey) throws PokerNotFoundException, NotAllowedException {
    PokerSession session = registry.getSession(sessionId)
            .orElseThrow(PokerNotFoundException::new);
    session.reset(adminKey);
    // Websocket message about played cards
  }
}