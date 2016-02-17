package de.iho.skynet;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import skynet.SkynetSubnet;
import skynet.SubnetBackdoor;

@RunWith(JUnitParamsRunner.class)
public class SkynetTest {

	public static List<Object[]> data() {
		final List<Object[]> parameters = new ArrayList<>();

		parameters.add(new Object[] { true, SkynetSubnet.createBackdoorToExistingSubnet(1) });
		parameters.add(new Object[] { true, SkynetSubnet.createBackdoorToExistingSubnet(2) });
		parameters.add(new Object[] { true, SkynetSubnet.createBackdoorToExistingSubnet(3) });

		for (int i = 2; i < 100; i++) {
			parameters.add(new Object[] { true, SkynetSubnet.createRandomSubnet(i) });
		}

		return parameters;
	}

	@Test
	@Parameters(method = "data")
	public void whenInputIsExistingSubnetAlphaThenWorldShouldBeSaved(boolean expected, SubnetBackdoor subnet) {
		Skynet skynet = new Skynet(subnet);
		boolean actual = skynet.saveTheWorld();

		assertEquals(expected, actual);
	}

}