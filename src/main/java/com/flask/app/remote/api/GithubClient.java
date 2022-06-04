package com.flask.app.remote.api;

import com.flask.app.remote.model.GithubModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.*;
import java.net.URI;
import java.util.List;


@FeignClient(value = "github", url = "https://api.github.com/")
public interface GithubClient {
    @RequestMapping(method = RequestMethod.GET)
    byte[] getAvatar(URI url);

    @RequestMapping(method = RequestMethod.GET, value = "/users/{login}", produces = "application/json")
    GithubModel getProfileDetails(@PathVariable("login") String loginId);
}
