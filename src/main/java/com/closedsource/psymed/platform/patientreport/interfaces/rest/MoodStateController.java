package com.closedsource.psymed.platform.patientreport.interfaces.rest;

import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.MoodState;
import com.closedsource.psymed.platform.patientreport.domain.services.MoodStateCommandService;
import com.closedsource.psymed.platform.patientreport.domain.services.MoodStateQueryService;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.resources.CreateMoodStateRecordResource;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.resources.MoodStateResource;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.transform.CreateMoodStateRecordCommandFromResourceAssembler;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.transform.MoodStateResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value="api/v1/mood-states", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Mood States", description = "Mood States Endpoints")
public class MoodStateController {
    private final MoodStateCommandService moodStateCommandService;
    private final MoodStateQueryService moodStateQueryService;

    public MoodStateController (MoodStateCommandService moodStateCommandService,
                                MoodStateQueryService moodStateQueryService) {
        this.moodStateCommandService = moodStateCommandService;
        this.moodStateQueryService = moodStateQueryService;
    }

    @PostMapping
        public ResponseEntity<MoodStateResource> createMoodState(@RequestBody CreateMoodStateRecordResource resource) {
        Optional<MoodState> moodState = moodStateCommandService
                .handle(CreateMoodStateRecordCommandFromResourceAssembler.toCommandFromResource(resource));
        return moodState.map(mood -> new ResponseEntity<>(MoodStateResourceFromEntityAssembler
                        .toResourceFromEntity(mood), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
