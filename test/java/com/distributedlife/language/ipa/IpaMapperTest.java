package com.distributedlife.language.ipa;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IpaMapperTest {

    private IpaMapper ipaMapper = new IpaMapper();

    @Test
    public void shouldMapReceivedPronunciationIpaToAnotherDialect() {
        String rp = "həˈləʊ̯";
        String genAm = "həˈloʊ̯";

        assertThat(ipaMapper.map(rp, Ipa.GeneralAmerican), is(genAm));
    }
}
