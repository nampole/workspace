package sample1;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;

public class SampleItemWriter implements ItemWriter<Object> {

	private static final Log log = LogFactory.getLog(SampleItemWriter.class);

	public void write(List<? extends Object> data) throws Exception {
		log.info(data);
	}

}
