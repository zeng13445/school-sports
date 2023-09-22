package com.formssi.zengzl.full;

import com.formssi.zengzl.base.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDateUtils {

    @Test
    public void testDayDiff() {
        long dayDiff = DateUtils.getDayDiff(LocalDate.of(2021, 7, 31),
                LocalDate.of(2021, 8, 1));

        log.info("相差天数{}", dayDiff);
    }
}
