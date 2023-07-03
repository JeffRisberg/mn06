package com.example.controllers;

import com.example.models.User;
import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/")
public class HelloWorldController {

  private static final Logger LOG = LoggerFactory.getLogger(HelloWorldController.class);

  @Inject private HelloWorldService helloWorldService;

  private String helloFromConfg;
  private String goodbyeFromConfig;

  public HelloWorldController(
      @Property(name = "hello.world.message") String helloFromConfg,
      @Property(name = "goodbye.world.message") String goodbyeFromConfig) {
    this.helloFromConfg = helloFromConfg;
    this.goodbyeFromConfig = goodbyeFromConfig;
  }

  @Get(uri = "/hello", produces = MediaType.TEXT_PLAIN)
  public String helloWorld() {
    LOG.info("helloWorld is running");
    return helloWorldService.helloFromService();
  }

  @Get(uri = "/goodbye", produces = MediaType.TEXT_PLAIN)
  public String goodbyeWorld() {
    LOG.info("goodbyeWorld is running");
    return goodbyeFromConfig;
  }

  @Get(uri = "/user", produces = MediaType.APPLICATION_JSON)
  public User userGet() {
    User data = new User("jim@gmail.com", "123456");
    return data;
  }

  @Post(uri = "/user", consumes = MediaType.APPLICATION_JSON)
  public HttpResponse<User> userPost(@Body User user) {
    System.out.println(user);
    return HttpResponse.created(user);
  }
}
