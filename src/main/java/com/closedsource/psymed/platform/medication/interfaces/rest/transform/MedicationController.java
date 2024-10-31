package com.closedsource.psymed.platform.medication.interfaces.rest.transform;

import com.closedsource.psymed.platform.medication.domain.model.commands.DeleteMedicationCommand;
import com.closedsource.psymed.platform.medication.domain.model.queries.GetAllMedicationsQuery;
import com.closedsource.psymed.platform.medication.domain.model.queries.GetMedicationByIdQuery;
import com.closedsource.psymed.platform.medication.domain.model.queries.GetMedicationByNameQuery;
import com.closedsource.psymed.platform.medication.domain.services.MedicationCommandService;
import com.closedsource.psymed.platform.medication.domain.services.MedicationQueryService;
import com.closedsource.psymed.platform.medication.interfaces.rest.resources.CreateMedicationResource;
import com.closedsource.psymed.platform.medication.interfaces.rest.resources.MedicationResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/medications", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Medication", description = "All medication related endpoints")
public class MedicationController {

    private final MedicationCommandService medicationCommandService;
    private final MedicationQueryService medicationQueryService;


    public MedicationController(MedicationCommandService medicationCommandService, MedicationQueryService medicationQueryService) {
        this.medicationCommandService = medicationCommandService;
        this.medicationQueryService = medicationQueryService;
    }


    @Operation(summary = "Create a new medication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @PostMapping
    public ResponseEntity<MedicationResource> createMedication(@RequestBody CreateMedicationResource createMedicationResource){
        var createMedicationCommand = CreateMedicationCommandFromResourceAssembler.toCommandFromResource(createMedicationResource);
        var medicationId = medicationCommandService.handle(createMedicationCommand);
        if (medicationId == null || medicationId == 0L) return ResponseEntity.badRequest().build();
        var getMedicationByIdQuery = new GetMedicationByIdQuery(medicationId);
        var medication = medicationQueryService.handle(getMedicationByIdQuery);
        if(medication.isEmpty()) return ResponseEntity.notFound().build();
        var medicationEntity = medication.get();
        var medicationSource = MedicationResourceFromEntityAssembler.toResourceFromEntity(medicationEntity);
        return new ResponseEntity<>(medicationSource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all medications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Courses found"),
            @ApiResponse(responseCode = "404", description = "Courses not found")
    })
    @GetMapping
    public ResponseEntity<List<MedicationResource>> getAllMedications(){
        var getAllMedicationsQuery = new GetAllMedicationsQuery();
        var medications = medicationQueryService.handle(getAllMedicationsQuery);
        var medicationResources = medications.stream()
                .map(MedicationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(medicationResources);
    }

    @Operation(summary = "Delete a medication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course deleted"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @DeleteMapping("/{medicationId}")
    public ResponseEntity<?> deleteMedication(@PathVariable Long medicationId){
        var deleteMedicationCommand = new DeleteMedicationCommand(medicationId);
        medicationCommandService.handle(deleteMedicationCommand);
        return ResponseEntity.ok("Medication with given id successfully deleted");
    }









}
