package com.acme.movingtomastery.controller;

import com.acme.movingtomastery.dto.MemberDTO;
import com.acme.movingtomastery.entity.Member;
import com.acme.movingtomastery.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.acme.movingtomastery.shared.Route.ROUTE_MEMBERS;

@RestController
@RequestMapping(path = ROUTE_MEMBERS)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<Member>> findAll(@RequestParam final Map<String, String> params) {
        if (params.containsKey("non-deleted") && Objects.equals(params.get("non-deleted"), "true")) {
            return new ResponseEntity<>(memberService.findNonDeleted(), null, HttpStatus.OK);
        }
        return new ResponseEntity<>(memberService.findAll(), null, HttpStatus.OK);
    }

    @GetMapping(
            path = "/single",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Member findById(@RequestParam final Map<String, String> params) {
        if (params.containsKey("paypal")) {
            return memberService.findByPaypal(params.get("paypal"));
        }
        return memberService.findById(UUID.fromString(params.get("id")));
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Member create(@RequestBody final MemberDTO memberDTO) {
        return memberService.create(memberDTO);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Member update(@RequestBody final Member member) {
        return memberService.update(member);
    }

    @DeleteMapping(
            path = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Member delete(@PathVariable final UUID id) {
        return memberService.delete(id);
    }

}
