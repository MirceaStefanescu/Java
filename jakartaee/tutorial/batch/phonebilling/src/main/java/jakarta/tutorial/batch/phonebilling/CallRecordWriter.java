package jakarta.tutorial.batch.phonebilling;

import jakarta.batch.api.chunk.ItemWriter;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.tutorial.batch.phonebilling.items.CallRecord;
import jakarta.tutorial.batch.phonebilling.items.PhoneBill;

import java.io.Serializable;
import java.util.List;

/* Writer batch artifact.
 * Add every call to a bill entity.
 */
@Dependent @Named("CallRecordWriter") public class CallRecordWriter implements ItemWriter {

    @PersistenceContext EntityManager em;

    public CallRecordWriter() {
    }

    @Override public void open(Serializable checkpoint) throws Exception {
        /* Clear all bills if this is not a restart of the job */
        if (checkpoint == null)
            em.clear();
    }

    @Override public void close() throws Exception {
    }

    @Override public void writeItems(List<Object> callList) throws Exception {

        for (Object callObject : callList) {
            CallRecord call = (CallRecord) callObject;
            PhoneBill bill = em.find(PhoneBill.class, call.getFromNumber());
            if (bill == null) {
                /* No bill for this customer yet, create one */
                bill = new PhoneBill(call.getFromNumber());
                bill.addCall(call);
                em.persist(bill);
            } else {
                /* Add call to existing bill */
                bill.addCall(call);
            }
        }
    }

    @Override public Serializable checkpointInfo() throws Exception {
        return new ItemNumberCheckpoint();
    }

}
