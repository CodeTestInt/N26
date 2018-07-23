package transaction;

public class TransactionStats {

    private double sum;
    private double avg;
    private double max;
    private double min;
    private int count;
    private long timeLastUpdated;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getAvg() {
        if (this.getSum() != 0) {
            return sum / count;
        } else {
            return 0;
        }
    }
        public void setAvg(double avg){
            this.avg = avg;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max){
            this.max = max;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min){
            this.min = min;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count){
            this.count = count;
        }

        public long getTimeLastUpdated() {
            return timeLastUpdated;
        }

        public void setTimeLastUpdated(long timeLastUpdated){
            this.timeLastUpdated = timeLastUpdated;
        }
    }

