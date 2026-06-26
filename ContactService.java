import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("Contact ID already exists");
        }
        contacts.put(contact.getContactId(), contact);
    }

    public void deleteContact(String contactId) {
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID does not exist");
        }
        contacts.remove(contactId);
    }

    public void updateFirstName(String contactId, String firstName) {
        getRequiredContact(contactId).setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        getRequiredContact(contactId).setLastName(lastName);
    }

    public void updatePhoneNumber(String contactId, String phoneNumber) {
        getRequiredContact(contactId).setPhoneNumber(phoneNumber);
    }

    public void updateAddress(String contactId, String address) {
        getRequiredContact(contactId).setAddress(address);
    }

    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }

    public int getContactCount() {
        return contacts.size();
    }

    private Contact getRequiredContact(String contactId) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID does not exist");
        }
        return contact;
    }
}
