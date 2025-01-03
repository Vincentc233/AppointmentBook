public class AppointmentBook {
    private boolean[][] schedule;


    private boolean isMinuteFree(int period, int minute) {
        return schedule[period - 1][minute];
    }

    public AppointmentBook(boolean[][] schedule) {
        this.schedule = schedule;
    }

    public void printPeriod(int period) {
        for (int i = 0; i < schedule[period - 1].length; i++)
            System.out.println(i + " " + schedule[period - 1][i]);
    }

    private void reserveBlock(int period, int startMinute, int duration) {
        for (int i = startMinute; i < startMinute + duration; i++) {
            schedule[period - 1][i] = false;

        }
    }

    /**
     * Searches for the first block of duration free minutes during period, as
     * described in
     * part (a). Returns the first minute in the block if such a block is found or
     * returns -1 if no
     * such block is found.
     * Preconditions: 1 <= period <= 8; 1 <= duration <= 60
     */
    public int findFreeBlock(int period, int duration) {
        int block = 0;
        for (int i = 0; i < 60; i++) {
            if (isMinuteFree(period, i)) {
                block++;
                if (block == duration) return i - duration + 1;
            } else block = 0;
        }
        return -1;
    }

    public boolean makeAppointment(int startPeriod, int endPeriod, int duration) {
        for (int i = startPeriod; i <= endPeriod; i++) {
            int freeBlock = findFreeBlock(i, duration);
            if (freeBlock > -1) {
                reserveBlock(i, freeBlock, duration);
                return true;
            }
        }
        return false;
    }

}