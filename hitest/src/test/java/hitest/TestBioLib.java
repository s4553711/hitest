package hitest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.ibms.hitest.model.BioLib;
import com.ibms.hitest.model.BwaLib;

public class TestBioLib {

	@Test
	public void testStdReference() {
		String parentFolder = "D:\\tmp";
		String testPath = parentFolder+"/human_g1k_v37";
		File testTarget = new File(testPath);
		try {
			FileUtils.deleteDirectory(testTarget);
		} catch (IOException e1) {
			fail("Fail before submitting testFolder");
		}
		testTarget.mkdirs();

		BioLib target = new BioLib();
		target.setPath(testPath);
		target.setRefName("ref");
		assertEquals(true, target.folderExist());
		
		File test1 = new File(testPath+"/ref.fa");
		File test2 = new File(testPath+"/ref.fa.fai");
		File test3 = new File(testPath+"/contig.flank.intervals");
		File test4 = new File(testPath+"/contig.flank.sort.intervals");
		try {
			test1.createNewFile();
			assertEquals(false, target.check());
			
			test2.createNewFile();
			assertEquals(false, target.check());
			
			test3.createNewFile();
			test4.createNewFile();
			assertEquals(true, target.check());
		} catch (IOException e) {
			fail("Create tmp file error");
		}
	}
	
	@Test
	public void testBwa() {
		String parentFolder = "D:\\tmp";
		String testPath = parentFolder+"/human_g1k_v37_bwa";
		File testTarget = new File(testPath);
		try {
			FileUtils.deleteDirectory(testTarget);
		} catch (IOException e1) {
			fail("Fail before submitting testFolder");
		}
		testTarget.mkdirs();
		
		BwaLib folder = new BwaLib();
		folder.setPath(testPath);
		folder.setRefName("ref");
		assertEquals(true, folder.folderExist());
		
	}

}
