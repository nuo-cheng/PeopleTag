/*
 * Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.getstarted.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// [START example]

/**
 * filter used when log out
 */
@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {

  /**
   * doget
   * @param req
   * @param resp
   * @throws IOException
   * @throws ServletException
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
    // you can also make an authenticated request to logout, but here we choose to
    // simply delete the session variables for simplicity
    // return current session if it exists. req.getSession(false) doesn't create a new session
    HttpSession session =  req.getSession(false);
    if (session != null) {
      session.invalidate();
    }
    // rebuild session
    // create a new session since no session exists now
    req.getSession();
  }
}
// [END example]
