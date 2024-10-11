package io.github.elcattivo13.ecpoker;

import io.github.elcattivo13.ecpoker.model.PokerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class EcPokerConfig {
  
	private static final Logger log = LoggerFactory.getLogger(EcPokerConfig.class);

	@Bean
	public PokerRegistry pokerRegistry() {
		return new PokerRegistry();
	}
	
	@Scheduled(fixedRate = 1000 * 60 * 60)
	public void cleanupRegistry() {
	  long n = pokerRegistry().cleanup();
	  log.info("{} Poker sessions were deleted during cleanup", n);
	}
}