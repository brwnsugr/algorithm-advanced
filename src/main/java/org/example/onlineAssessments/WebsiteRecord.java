package org.example.onlineAssessments;

/**
 * As an intern at Amazon, you have been assigned a task to implement the sign-in pages in the Amazon Dummy Website.
 *
 * There are three sign-in pages, each with its own API:
 *
 * Function	Register	Login	Logout
 * API Request	register <username> <password>	login <username> <password>	logout <username>
 * Returns	- If the registration was successful, Registered Successfully	- If the login was successful, Logged In Successfully	- If the logout was successful, Logged Out Successfully
 * - If the login was unsuccessful,	- If the given username logs out of the website
 * Given a log of API requests, return the list of returns from the mock website.
 *
 * Notes:
 *
 * Initially, there are no users registered.
 * If a user is already logged in and makes a login request, the new request is unsuccessful. The original login remains active.
 * Each log is an API request and is in one of the three allowed formats.
 * The order of execution of each request is the same as the order of input.
 * The usernames and passwords are case-sensitive.
 * Example
 *
 * The website receives the following API requests in order:
 *
 * register user05 qwerty
 * A new user with the username "user05" is registered to the website with the password "qwerty".
 * The return value is Registered Successfully
 * login user05 qwerty
 * There is a user with that username.
 * The password matches that user's password.
 * The user is not already logged in.
 * The login is successful and the return value is Logged In Successfully
 * logout user05
 * ...
 * Function Description
 *
 * Complete the function returnRecords in the editor below.
 *
 * returnRecords has the following parameter:
 *
 * string attempts[n]: each of the API requests
 * Returns
 *
 * string[n]: an array of strings where ith string is the return value of the ith API request
 * Constraints
 *
 * ref: https://csoahelp.com/2024/02/05/amazon-oline-accessment-record/
 */
public class WebsiteRecord {


}
