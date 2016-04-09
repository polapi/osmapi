package de.westnordost.osmapi.user;

import junit.framework.TestCase;

import java.io.UnsupportedEncodingException;
import java.util.List;

import de.westnordost.osmapi.xml.XmlTestUtils;

public class PermissionsParserTest extends TestCase
{
	public void testPermissionsParser() throws UnsupportedEncodingException
	{
		String xml =
				"<permissions>" +
				"	<permission name=\"allow_xyz\" />" +
				"	<permission name=\"allow_abc\" />" +
				"</permissions>";

		List<String> permissions = new PermissionsParser().parse(XmlTestUtils.asInputStream(xml));
		assertTrue(permissions.contains("allow_xyz"));
		assertTrue(permissions.contains("allow_abc"));
		assertFalse(permissions.contains("allow_somethingElse"));
	}
}
