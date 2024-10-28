package com.closedsource.psymed.platform.patientreport.application.outboundservices.acl;

import com.closedsource.psymed.platform.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalProfileService {
    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public boolean existsPatientById(Long patientId) {
        return  profilesContextFacade.verifyPatientProfile(patientId);
    }
}
