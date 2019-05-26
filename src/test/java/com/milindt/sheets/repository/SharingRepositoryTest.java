package com.milindt.sheets.repository;

import com.milindt.sheets.model.Sharing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SharingRepositoryTest {

    @Autowired
    private SharingRepository sharingRepository;

    @Test
    void givenSharing_whenSave_thenGetOk() {
        Sharing sharing = new Sharing();
        sharing.setId(1l);
        sharing.setEmailIds(Collections.singletonList("myEmail@mail.com"));
        sharing.setSelections(Collections.singletonList("sheet1!A1:Z99"));
        sharingRepository.save(sharing);

        Sharing persistedSharing = sharingRepository.findAll().get(0);
        assertThat(persistedSharing)
                .isNotNull();
        assertThat(persistedSharing.getEmailIds())
                .containsOnly("myEmail@mail.com");
        assertThat(persistedSharing.getSelections())
                .containsOnly("sheet1!A1:Z99");
    }

}
