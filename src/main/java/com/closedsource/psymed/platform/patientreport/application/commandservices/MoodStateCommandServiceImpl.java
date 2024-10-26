    package com.closedsource.psymed.platform.patientreport.application.commandservices;

    import com.closedsource.psymed.platform.patientreport.application.outboundservices.acl.ExternalProfileService;
    import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.MoodState;
    import com.closedsource.psymed.platform.patientreport.domain.model.commands.CreateMoodStateRecordCommand;
    import com.closedsource.psymed.platform.patientreport.domain.services.MoodStateQueryService;
    import com.closedsource.psymed.platform.patientreport.infrastructure.persistence.jpa.repositories.MoodStateRecordRepository;
    import com.closedsource.psymed.platform.patientreport.domain.services.MoodStateCommandService;
    import org.springframework.stereotype.Service;

    import java.util.Optional;

    @Service
    public class MoodStateCommandServiceImpl implements MoodStateCommandService {
        private final MoodStateRecordRepository moodStateRecordRepository;
        private final ExternalProfileService externalProfileService;
        public MoodStateCommandServiceImpl(MoodStateRecordRepository moodStateRepository, ExternalProfileService externalProfileService) {
            this.moodStateRecordRepository = moodStateRepository;
            this.externalProfileService = externalProfileService;
        }
        @Override
        public Optional<MoodState> handle(CreateMoodStateRecordCommand command) {
//            #TODO: Implement this method to fetch if the patient exists
            var patientId = externalProfileService.existsPatientById(command.patientId());
            if(patientId) {
                var moodStateRecord = new MoodState(command.patientId(), command.moodStatus());
                var lastMoodStateRecord = moodStateRecordRepository.findAllByPatientId(moodStateRecord.getPatientId()).stream().reduce((first, second) -> second).orElse(null);
                if(lastMoodStateRecord != null) moodStateRecord.validateDay(lastMoodStateRecord);
                var createdMoodStateRecord = moodStateRecordRepository.save(moodStateRecord);
                return Optional.of(createdMoodStateRecord);
            }
            else {
                throw new IllegalArgumentException("Patient does not exist");
            }



        }
    }
