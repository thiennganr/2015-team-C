/* 
* Copyright (C) 2003-2015 eXo Platform SAS.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see http://www.gnu.org/licenses/ .
*/
package org.exoplatform.addons.codefest.team_c.dao.impl;

import org.exoplatform.addons.codefest.team_c.dao.KittenSaviorDAO;
import org.exoplatform.addons.codefest.team_c.domain.Choice;
import org.exoplatform.addons.codefest.team_c.domain.Meeting;
import org.exoplatform.addons.codefest.team_c.domain.Option;
import org.exoplatform.addons.codefest.team_c.domain.User;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import javax.inject.Singleton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by The eXo Platform SAS
 * Author : Thibault Clement
 * tclement@exoplatform.com
 * 7/6/15
 */
@Singleton
public class KittenSaviorDAOImpl implements KittenSaviorDAO {

  private static final Log LOG = ExoLogger.getExoLogger(KittenSaviorDAOImpl.class);

  private static Map<Long, Meeting> meetings;
  private static Map<Long, Option> options;
  private static Map<Long, Choice> choices;
  private static Map<String, User> users;

  public KittenSaviorDAOImpl() {
    meetings = new HashMap<Long, Meeting>();
    options = new HashMap<Long, Option>();
    choices = new HashMap<Long, Choice>();
    users = new HashMap<String , User>();
    initDatas();
  }

  private void initDatas() {

    try {

      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      Date d1 = sdf.parse("09/07/2015 10:00");
      Date d2 = sdf.parse("09/07/2015 11:00");
      Date d3 = sdf.parse("09/07/2015 12:00");
      Date d4 = sdf.parse("09/07/2015 13:00");
      Date d5 = sdf.parse("10/07/2015 14:00");
      Date d6 = sdf.parse("10/07/2015 15:00");
      Date d7 = sdf.parse("11/07/2015 16:00");
      Date d8 = sdf.parse("11/07/2015 17:00");

      //User
      User root = new User("root", "Europe/Paris");
root.setFirstName("Thibault");
root.setLastName("Clement");
      User john = new User("john", "Asia/Saigon");
      john.setFirstName("Philippe");
      john.setLastName("Aristote");
      User mary = new User("mary", "Africa/Tunis");
      User fred = new User("fred", "Europe/Paris");
      User patrice = new User("patrice", "Europe/Paris");
      users.put(root.getName(), root);
      users.put(john.getName(), john);
      users.put(mary.getName(), mary);
      users.put(fred.getName(), fred);
      users.put(patrice.getName(), patrice);

      //Choice
      Choice rootYes = new Choice("root", true);
      Choice johnYes = new Choice("john", true);
      Choice maryYes = new Choice("mary", true);
      Choice fredYes = new Choice("fred", true);
      Choice patYes = new Choice("patrice", true);
      Choice johnNo = new Choice("john", false);
      Choice rootNo = new Choice("root", false);
      Choice maryNo = new Choice("mary", false);
      Choice fredNo = new Choice("fred", false);
      Choice patNo = new Choice("patrice", false);
      choices.put(rootYes.getId(), rootYes);
      choices.put(johnYes.getId(), johnYes);
      choices.put(maryYes.getId(), maryYes);
      choices.put(fredYes.getId(), fredYes);
      choices.put(patYes.getId(), patYes);
      choices.put(rootNo.getId(), rootNo);
      choices.put(johnNo.getId(), johnNo);
      choices.put(maryNo.getId(), maryNo);
      choices.put(fredNo.getId(), fredNo);
      choices.put(patNo.getId(), patNo);

      //Option
      Option withRootNoJohn = new Option(new ArrayList<Long>(Arrays.asList(rootYes.getId(), johnNo.getId())), d1, d2);
      Option withRootAndJohn = new Option(new ArrayList<Long>(Arrays.asList(rootYes.getId(), johnYes.getId())), d3, d4);
      Option withJohnNoRoot = new Option(new ArrayList<Long>(Arrays.asList(rootNo.getId(), johnYes.getId())), d5, d6);
      Option withNoRootNoJohn = new Option(new ArrayList<Long>(Arrays.asList(rootNo.getId(), johnNo.getId())), d7, d8);
      Option forBinch = new Option(new ArrayList<Long>(Arrays.asList(rootYes.getId(), johnYes.getId())), d1, d2);
      Option withFredWithPat = new Option(new ArrayList<Long>(Arrays.asList(fredYes.getId(), patYes.getId())), d3, d4);
      Option withFredNoPat = new Option(new ArrayList<Long>(Arrays.asList(fredYes.getId(), patNo.getId())), d5, d6);
      Option withMaryWithFred = new Option(new ArrayList<Long>(Arrays.asList(maryYes.getId(), fredYes.getId())), d7, d8);
      options.put(withRootNoJohn.getId(), withRootNoJohn);
      options.put(withRootAndJohn.getId(), withRootAndJohn);
      options.put(withJohnNoRoot.getId(), withJohnNoRoot);
      options.put(withNoRootNoJohn.getId(), withNoRootNoJohn);
      options.put(forBinch.getId(), forBinch);
      options.put(withFredWithPat.getId(), withFredWithPat);
      options.put(withFredNoPat.getId(), withFredNoPat);
      options.put(withMaryWithFred.getId(), withMaryWithFred);


      //Meeting
      Meeting meeting1 = new Meeting();
      meeting1.setCreator(root);
      meeting1.setTitle("Nap with Portal");
      meeting1.setDescription("Portal team is too tired, they need a nap.");
      meeting1.setStatus("opened");
      meeting1.setOptions(new ArrayList<Long>(Arrays.asList(withRootNoJohn.getId(), withRootAndJohn.getId(), withFredNoPat.getId())));
      meeting1.setParticipants(new ArrayList<String>(Arrays.asList(root.getName(), john.getName(), fred.getName(), patrice.getName())));

      Meeting meeting2 = new Meeting();
      meeting2.setCreator(john);
      meeting2.setTitle("Bia Hoi tonight");
      meeting2.setDescription("After a hardworking day, let's take a cool binch");
      meeting2.setStatus("closed");
      meeting2.setFinalOption(forBinch);
      meeting2.setOptions(new ArrayList<Long>(Arrays.asList(withJohnNoRoot.getId(), withNoRootNoJohn.getId(), forBinch.getId(), withFredWithPat.getId())));
      meeting2.setParticipants(new ArrayList<String>(Arrays.asList(root.getName(), john.getName(), fred.getName(), patrice.getName())));

      //Meeting
      Meeting meeting3 = new Meeting();
      meeting3.setCreator(fred);
      meeting3.setTitle("Pirate Hiring");
      meeting3.setDescription("Why join the navy when you can be a bloody pirate?");
      meeting3.setStatus("opened");
      meeting3.setOptions(new ArrayList<Long>(Arrays.asList(withRootAndJohn.getId(), withRootAndJohn.getId(), withMaryWithFred.getId())));
      meeting3.setParticipants(new ArrayList<String>(Arrays.asList(root.getName(), john.getName(), mary.getName(), fred.getName())));

      //Meeting
      Meeting meeting4 = new Meeting();
      meeting4.setCreator(patrice);
      meeting4.setTitle("Moon launch");
      meeting4.setDescription("Let's get ready to send kittens to the moooon!");
      meeting4.setStatus("opened");
      meeting4.setOptions(new ArrayList<Long>(Arrays.asList(withRootNoJohn.getId(), withJohnNoRoot.getId(), withFredWithPat.getId())));
      meeting4.setParticipants(new ArrayList<String>(Arrays.asList(root.getName(), john.getName(), fred.getName(), patrice.getName())));

      meetings.put(meeting1.getId(), meeting1);
      meetings.put(meeting2.getId(), meeting2);
      meetings.put(meeting3.getId(), meeting3);
      meetings.put(meeting4.getId(), meeting4);

    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  //////////////////////////
  //
  // MEETING
  //
  //////////////////////////

  @Override
  public Meeting getMeetingById(Long id) {
    return meetings.get(id);
  }

  @Override
  public Meeting createMeeting(Meeting meeting) {
    return meetings.put(meeting.getId(), meeting);
  }

  @Override
  public Meeting updateMeeting(Meeting meeting) {
    return meetings.put(meeting.getId(), meeting);
  }

  @Override
  public void deleteMeeting(Meeting meeting) {
    meetings.remove(meeting.getId());
  }

  @Override
  public List<Meeting> getMeetingByUser(User user) {
    List<Meeting> userMeetings = new ArrayList<Meeting>();
    for (Long meetingId: this.meetings.keySet()) {
      LOG.info("Meeting title = "+meetings.get(meetingId).getTitle());
      LOG.info("Meeting participants = ");
      for (String participant : meetings.get(meetingId).getParticipants()) LOG.info(participant);
      LOG.info("User = "+user);
      LOG.info("Username = "+user.getName());
      if (meetings.get(meetingId).getParticipants().contains(user.getName())) {
        userMeetings.add(meetings.get(meetingId));
      }
    }
    return userMeetings;
  }

  @Override
  public void addOptionsToMeeting(Meeting meeting, Option option) {
    addOptionToMeetingById(meeting.getId(), option);
  }

  @Override
  public void addOptionToMeetingById(Long meetingId, Option option) {
    options.put(option.getId(), option);
    meetings.get(meetingId).getOptions().add(option.getId());
  }

  //////////////////////////
  //
  // USER
  //
  //////////////////////////

  @Override
  public User getUserById(Long id) {
    return users.get(id);
  }

  @Override
  public User createUser(User user) {
    return users.put(user.getName(), user);
  }

  @Override
  public User updateUser(User user) {
    return createUser(user);
  }

  @Override
  public void deleteUser(User user) {
    users.remove(user.getName());
  }

  @Override
  public User getUserByUsername(String username) {
    return users.get(username);
  }

  @Override
  public String getTimezoneByUser(String username) {
    return users.get(username).getTimezone();
  }

  @Override
  public List<User> getParticipantsByMeetingId(Long meetingId) {
    List<User> participants = new ArrayList<User>();
    for (String userId : meetings.get(meetingId).getParticipants()) {
      participants.add(users.get(userId));
    }
    return participants;
  }



  //////////////////////////
  //
  // OPTION
  //
  //////////////////////////

  @Override
  public Option getOptionById(Long id) {
    return options.get(id);
  }

  @Override
  public Option createOption(Option option) {
    return options.put(option.getId(), option);
  }

  @Override
  public Option updateOption(Option option) {
    return options.put(option.getId(), option);
  }

  @Override
  public void deleteOption(Option option) {
    options.remove(option.getId());
  }

  @Override
  public List<Option> getOptionByMeetingId(Long meetingId) {
    LOG.info("#### meetingID: "+meetingId);
    List<Option> meetingOptions = new ArrayList<Option>();
    for (Long optionId : meetings.get(meetingId).getOptions()) {
      meetingOptions.add(options.get(optionId));
    }
    return meetingOptions;
  }

  @Override
  public void addChoiceToOption(Option option, Choice choice) {
    addChoiceToOptionById(option.getId(), choice);
  }

  @Override
  public void addChoiceToOptionById(Long optionId, Choice choice) {
    choices.put(choice.getId(), choice);
    options.get(optionId).getChoices().add(choice.getId());
  }

  //////////////////////////
  //
  // CHOICE
  //
  //////////////////////////

  @Override
  public Choice getChoiceById(Long id) {
    return choices.get(id);
  }

  @Override
  public Choice createChoice(Choice choice) {
    return choices.put(choice.getId(), choice);
  }

  @Override
  public Choice updateChoice(Choice choice) {
    return choices.put(choice.getId(), choice);
  }

  @Override
  public void deleteChoice(Choice choice) {
    choices.remove(choice.getId());
  }

}

