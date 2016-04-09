package de.westnordost.osmapi.map.changes;

import junit.framework.TestCase;

import java.io.UnsupportedEncodingException;

import de.westnordost.osmapi.SingleElementHandler;
import de.westnordost.osmapi.map.data.Element;
import de.westnordost.osmapi.xml.XmlTestUtils;

public class MapDataDiffParserTest extends TestCase
{
	public void testEmpty() throws UnsupportedEncodingException
	{
		DiffElement e = parseOne("<diffResult></diffResult>");

		assertNull(e);
	}

	public void testFields() throws UnsupportedEncodingException
	{
		DiffElement e = parseOne("<diffResult><node old_id='1' new_id='2' new_version='3'/></diffResult>");

		assertEquals(1, e.clientId);
		assertEquals(2, (long) e.serverId);
		assertEquals(3, (int) e.serverVersion);
	}

	public void testNullableFields() throws UnsupportedEncodingException
	{
		DiffElement e = parseOne("<diffResult><node old_id='1'/></diffResult>");

		assertEquals(1, e.clientId);
		assertNull(e.serverId);
		assertNull(e.serverVersion);
	}

	public void testNode() throws UnsupportedEncodingException
	{
		DiffElement e = parseOne("<diffResult><node old_id='1'/></diffResult>");
		assertEquals(Element.Type.NODE, e.type);
	}

	public void testWay() throws UnsupportedEncodingException
	{
		DiffElement e = parseOne("<diffResult><way old_id='1'/></diffResult>");
		assertEquals(Element.Type.WAY, e.type);
	}

	public void testRelation() throws UnsupportedEncodingException
	{
		DiffElement e = parseOne("<diffResult><relation old_id='1'/></diffResult>");
		assertEquals(Element.Type.RELATION, e.type);
	}

	private DiffElement parseOne(String xml) throws UnsupportedEncodingException
	{
		SingleElementHandler<DiffElement> handler = new SingleElementHandler<>();
		new MapDataDiffParser(handler).parse(XmlTestUtils.asInputStream(xml));
		return handler.get();
	}
}
