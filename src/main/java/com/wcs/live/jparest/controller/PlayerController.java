package com.wcs.live.jparest.controller;

import com.wcs.live.jparest.model.Player;
import com.wcs.live.jparest.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Controller
public class PlayerController {


    @Autowired
    private PlayerService service;

    @GetMapping("/players")
    @ResponseBody
    public List<Player> list() {
        return service.list();
    }

    @GetMapping("/players/{uuid}")
    @ResponseBody
    public Player get(@PathVariable UUID uuid) {
        return service
                .find(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find player with uuid> " + uuid));
    }

    @PostMapping("/players")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Player create(@RequestBody Player player) {
        return service.create(player);
    }

    @PutMapping("/players")
    @ResponseBody
    public Player update(@RequestBody Player player) {
        if (player.getUuid() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "missing uuid");
        else return service.update(player);
    }

    @DeleteMapping("/players/{uuid}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        boolean isDeleted = service.delete(uuid);
        if (isDeleted) return null;
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not delete player with uuid> " + uuid);

    }

}
