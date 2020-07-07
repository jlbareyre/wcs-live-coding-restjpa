package com.wcs.live.jparest.controller;

import com.wcs.live.jparest.model.Team;
import com.wcs.live.jparest.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Controller
public class TeamController {


    @Autowired
    private TeamService service;

    @GetMapping("/teams")
    @ResponseBody
    public List<Team> list() {
        return service.list();
    }

    @GetMapping("/team/{uuid}")
    @ResponseBody
    public Team get(@PathVariable UUID uuid) {

        return service
                .find(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find team with uuid> " + uuid));
    }

    @PostMapping("/teams")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Team create(@RequestBody Team team) {
        return service.create(team);
    }

    @PutMapping("/teams")
    @ResponseBody
    public Team update(@RequestBody Team team) {
        if (team.getUuid() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "missing uuid");
        else return service.update(team);
    }

    @DeleteMapping("/teams/{uuid}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        boolean isDeleted = service.delete(uuid);
        if (isDeleted) return null;
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not delete team with uuid> " + uuid);

    }

}
