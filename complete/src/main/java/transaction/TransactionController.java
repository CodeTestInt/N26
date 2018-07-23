    package transaction;

    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.ArrayList;
    import java.util.List;

    @RestController
    public class TransactionController {


       List<Transaction> txnList = new ArrayList<>();
       TransactionStats txnStatus = new TransactionStats();

        @RequestMapping(value="/transactions",method = RequestMethod.POST)
        public ResponseEntity handleTransaction(@RequestBody Transaction transaction) {

            /*
            to address the constraint in the problem to return the transaction status in O(1) complexity,
            all the calculations are done in the POST call itself and the result is stored
             */
                double max =0.00;
                double min =0.00;
                double sum =0.00;
                if(transaction.getTimestamp() - System.currentTimeMillis() <= 60000) {
                    synchronized (txnList) {
                        txnList.add(transaction);
                        for (Transaction txn : txnList) {
                            if (txn.getTimestamp() < (System.currentTimeMillis() - 60000)) {
                                txnList.remove(txn);
                            }
                            if (txn.getAmount() < min) {
                                min = txn.getAmount();
                            }
                            if (txn.getAmount() > max) {

                                max = txn.getAmount();
                            }
                            sum += txn.getAmount();
                        }

                        txnStatus.setSum(sum);
                        txnStatus.setAvg(sum / txnList.size());
                        txnStatus.setCount(txnList.size());
                        txnStatus.setMin(min);
                        txnStatus.setMax(max);
                        return new ResponseEntity(HttpStatus.ACCEPTED);
                    }
                }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        @RequestMapping(value = "/statistics",method = RequestMethod.GET)
        public ResponseEntity getTransaction(){
            ResponseEntity<TransactionStats> responseEntity = new ResponseEntity<>(txnStatus,HttpStatus.OK);
            return responseEntity;
        }
    }
