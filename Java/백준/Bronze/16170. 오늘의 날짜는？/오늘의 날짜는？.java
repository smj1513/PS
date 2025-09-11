import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
	
	public static void main(String[] args) {
		
		ZonedDateTime utcNow = ZonedDateTime.now(ZoneId.of("UTC"));
		
		int year = utcNow.getYear();
		int month = utcNow.getMonthValue();
		int day = utcNow.getDayOfMonth();
		
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);

	}

}
