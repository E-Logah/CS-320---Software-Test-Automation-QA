import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private Contact createContact(String id) {
        return new Contact(id, "Ernest", "Logah", "1234567890", "123 Main Street");
    }

    @Test
    void testAddContactSuccessfully() {
        ContactService service = new ContactService();
        Contact contact = createContact("C12345");
        service.addContact(contact);
        assertEquals(contact, service.getContact("C12345"));
        assertEquals(1, service.getContactCount());
    }

    @Test
    void testCannotAddNullContact() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    @Test
    void testCannotAddDuplicateContactId() {
        ContactService service = new ContactService();
        service.addContact(createContact("C12345"));
        assertThrows(IllegalArgumentException.class, () -> service.addContact(createContact("C12345")));
    }

    @Test
    void testDeleteContactSuccessfully() {
        ContactService service = new ContactService();
        service.addContact(createContact("C12345"));
        service.deleteContact("C12345");
        assertNull(service.getContact("C12345"));
        assertEquals(0, service.getContactCount());
    }

    @Test
    void testCannotDeleteContactThatDoesNotExist() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("C99999"));
    }

    @Test
    void testUpdateAllContactFieldsSuccessfully() {
        ContactService service = new ContactService();
        service.addContact(createContact("C12345"));
        service.updateFirstName("C12345", "John");
        service.updateLastName("C12345", "Smith");
        service.updatePhoneNumber("C12345", "0987654321");
        service.updateAddress("C12345", "456 Oak Road");
        assertEquals("John", service.getContact("C12345").getFirstName());
        assertEquals("Smith", service.getContact("C12345").getLastName());
        assertEquals("0987654321", service.getContact("C12345").getPhoneNumber());
        assertEquals("456 Oak Road", service.getContact("C12345").getAddress());
    }

    @Test
    void testCannotUpdateContactThatDoesNotExist() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("C99999", "John"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("C99999", "Smith"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhoneNumber("C99999", "0987654321"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("C99999", "456 Oak Road"));
    }

    @Test
    void testUpdateRejectsInvalidFieldValues() {
        ContactService service = new ContactService();
        service.addContact(createContact("C12345"));
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("C12345", null));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("C12345", null));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhoneNumber("C12345", "12345"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("C12345", null));
    }
}
