package com.cauanlagrotta.dynamodb.controller;

import com.cauanlagrotta.dynamodb.entity.PlayerHistoryEntity;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/players")
public class PlayerController {
  private final DynamoDbTemplate dynamoDbTemplate;

  public PlayerController(DynamoDbTemplate dynamoDbTemplate) {
    this.dynamoDbTemplate = dynamoDbTemplate;
  }

  @PostMapping("/{username}/games")
  public ResponseEntity<Void> save(@PathVariable("username") String username,
                                   @RequestBody ScoreDTO scoreDTO){

    var entity = PlayerHistoryEntity.fromScore(username, scoreDTO);
    dynamoDbTemplate.save(entity);

    return ResponseEntity.ok().build();
  }
}
