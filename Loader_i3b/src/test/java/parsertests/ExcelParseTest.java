package parsertests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import org.assertj.core.util.Files;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.agents.AbstractAgent;
import es.uniovi.asw.parser.readers.ExcelReadList;

public class ExcelParseTest {

	private Set<AbstractAgent> readData;
	
	@Before
	public void clearDatabase() {
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("Agents");
		db.getCollection("agents").deleteMany(new Document());
	}
	
	@Test
	public void testParse() {
		String result = "[Person Agent [Name=PersonName, location=45,-1, "
				+ "email=person@example.com, identifier=id1]]";

		ReadList rl = new ExcelReadList();
		rl.parseMaster("src/test/resources/masterTest.csv");
		readData = rl.parse("src/test/resources/test2.xlsx");

		assertTrue(readData.toString().equals(result)
				);
	}
	
	@Test
	public void testParsePersonNoCoordinates() {
		String result = "[Person Agent [Name=PersonName, location=null, "
				+ "email=person@example.com, identifier=id1]]";

		ReadList rl = new ExcelReadList();
		rl.parseMaster("src/test/resources/masterTest.csv");
		readData = rl.parse("src/test/resources/test3.xlsx");

		assertTrue(readData.toString().equals(result)
				);
	}


	@Test
	/**
	 * Checks whether the file exists or not.
	 * 
	 */
	public void fileNotFound() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("archivoQueNoExiste");

		assertTrue(file.exists());
		Files.delete(file);
	}

	@Test
	/**
	 * Checks if the report is generated successfully.
	 * 
	 */
	public void testNoDNI() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test3.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the excel doesn't have a
	 * name
	 * 
	 */
	@Test
	public void testNoName() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test4.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the excel doesn't have a
	 * surname
	 * 
	 */
	@Test
	public void testNoSurname() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test5.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the excel doesn't have a
	 * birthdate
	 * 
	 */
	@Test
	public void testNoBirthDate() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test6.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the excel doesn't have the
	 * NIF
	 * 
	 */
	@Test
	public void testNoNIF() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test7.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the excel doesn't have an
	 * address
	 * 
	 */
	@Test
	public void testNoAddress() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test8.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the excel has a blank row.
	 * 
	 */
	@Test
	public void testNoRow() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test10.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

	/**
	 * Checks that the report file is generated when the citizen is duplicated
	 * 
	 */
	@Test
	public void testNoDuplicate() {
		SimpleDateFormat formatofilename = new SimpleDateFormat("dd-MM-yyyy",
				Locale.getDefault());
		String filename = formatofilename.format(new Date()) + ".txt";
		File file = new File(filename);

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test9.xlsx");

		assertTrue(file.exists());
		Files.delete(file);
	}

}
