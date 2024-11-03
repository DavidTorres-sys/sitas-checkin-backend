package com.sitas.checkin.services.email.service;

/**
 * This interface defines methods for sending emails.
 */
public interface IEmailService {
    /**
     * Sends an email to the specified user email address.
     *
     * @param userEmail the email address of the user to whom the email will be sent
     */
    public void sendEmail(String userEmail);
}
