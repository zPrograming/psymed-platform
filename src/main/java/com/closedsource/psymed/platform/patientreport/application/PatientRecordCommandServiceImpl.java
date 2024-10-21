    package com.closedsource.psymed.platform.patientreport.application;

    import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.MoodStateRecord;
    import com.closedsource.psymed.platform.patientreport.domain.model.commands.CreateMoodStateRecordCommand;
    import com.closedsource.psymed.platform.patientreport.infrastructure.persistence.jpa.repositories.MoodStateRecordRepository;
    import com.closedsource.psymed.platform.patientreport.services.PatientRecordCommandService;

    public class PatientRecordCommandServiceImpl implements PatientRecordCommandService {
        private final MoodStateRecordRepository moodStateRecordRepository;

        public PatientRecordCommandServiceImpl(MoodStateRecordRepository moodStateRepository) {
            this.moodStateRecordRepository = moodStateRepository;
        }
        @Override
        public void recordMoodState(CreateMoodStateRecordCommand command) {
//            #TODO: Implement this method to fetch if the patient exists
//            var patientId = externalProfileService.existsById(command.patientId());
//            if(patientId) {
//                var moodStateRecord = new MoodStateRecord(command.patientId(), command.moodStatus());
//                var lastMoodStateRecord = moodStateRecordRepository.findAllByPatientId(moodStateRecord.getPatientId()).stream().reduce((first, second) -> second).orElse(null);
//                moodStateRecord.validateDay(lastMoodStateRecord);
//                moodStateRecordRepository.save(moodStateRecord);
//            }
//            else {
//                throw new IllegalArgumentException("Patient does not exist");
//            }



        }
    }
