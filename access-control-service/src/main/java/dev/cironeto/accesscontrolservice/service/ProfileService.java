package dev.cironeto.accesscontrolservice.service;

import dev.cironeto.accesscontrolservice.dto.ProfileRequestBody;
import dev.cironeto.accesscontrolservice.dto.ProfileResponseBody;
import dev.cironeto.accesscontrolservice.exception.BadRequestException;
import dev.cironeto.accesscontrolservice.model.Profile;
import dev.cironeto.accesscontrolservice.repository.ProfileRepository;
import dev.cironeto.accesscontrolservice.validation.BeanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileResponseBody create(ProfileRequestBody dto) {
        BeanValidator.validate(dto);

        Profile profile = profileRepository.findByName(dto.getName());
        if (profile != null){
            throw new BadRequestException(String.format("Profile already exists. ID: %d",  profile.getId()));
        }else {
            Profile entity = new Profile();
            entity.setName(dto.getName());

            Profile savedEntity = profileRepository.save(entity);
            return new ProfileResponseBody(savedEntity);
        }
    }

    public List<ProfileResponseBody> findAll(){
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream().map(p -> new ProfileResponseBody(p)).collect(Collectors.toList());
    }

    public ProfileResponseBody findById(Long id){
        Optional<Profile> profileOptional = profileRepository.findById(id);
        Profile profile = profileOptional.orElseThrow(() -> new BadRequestException("ID not found"));
        return new ProfileResponseBody(profile);
    }

    public ProfileResponseBody update(Long id, ProfileRequestBody dto){
        try {
            Profile profile = profileRepository.getById(id);
            profile.setName(dto.getName());
            Profile savedEntity = profileRepository.save(profile);
            return new ProfileResponseBody(savedEntity);
        } catch (Exception e){
            throw new BadRequestException("ID not found");
        }
    }

    public void delete(Long id){
        try {
            profileRepository.deleteById(id);
        } catch (Exception e){
            throw new BadRequestException("ID not found");
        }
    }
}
