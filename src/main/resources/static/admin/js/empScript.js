$(document).ready(function() {
    const loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));
    console.log(loggedInUser);

    if (loggedInUser && loggedInUser.id) {
        fetchEmpDetail(loggedInUser.id);
        showEmployeeAttendance(loggedInUser.id);
        fetchEmpDetailById(loggedInUser.id);
    } else {
        console.error("No logged-in user found");
    }
});

// Fetch employee details (remains the same)
function fetchEmpDetail(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/employees/" + id,
        success: function(data) {
            console.log("User Details:", data);
            $('#empName').text(data.fName + ' ' + data.lName);
        },
        error: function(error) {
            console.error("Error fetching User details:", error);
        }
    });
}

// Function for Check-In
function checkIn() {
    const loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));

    $.ajax({
        type: 'POST',
        url: "/checkin/" + loggedInUser.id, // Call the check-in API
        success: function(response) {
            alert("Checked In successfully!");

            // Hide the Check In button and show the Check Out button
            document.getElementById('checkInButton').style.display = 'none';
            document.getElementById('checkOutButton').style.display = 'block';
        },
        error: function(xhr, status, error) {
            alert("Error checking in: " + xhr.responseText);
            console.log("Error checking in: " + xhr.responseText);
        }
    });
}

// Function for Check-Out
function checkOut() {
    const loggedInUser = JSON.parse(localStorage.getItem('loggedInUser'));

    $.ajax({
        type: 'POST',
        url: "/checkout/" + loggedInUser.id, // Call the check-out API
        success: function(response) {
            alert("Checked Out successfully! Total hours worked: " + response);

            // Hide the Check Out button after successful check-out
            document.getElementById('checkOutButton').style.display = 'none';
        },
        error: function(xhr, status, error) {
            alert("Error checking out: " + xhr.responseText);
            console.log("Error checking out: " + xhr.responseText);
        }
    });
}


function fetchEmpDetail(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/employees/" + id,
        success: function(data) {
            console.log("User Details:", data);
            $('#empName').text(data.fName + ' ' + data.lName);
        },
        error: function(error) {
            console.error("Error fetching User details:", error);
        }
    });
}

function fetchEmpDetailById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/employees/" + id,
        success: function(data) {
            console.log("User Details:", data);
            if (data) {
                // Populating the form fields with employee details
                $("#exampleInputFirstname1").val(data.fName);
                $("#exampleInputLastname1").val(data.lName);
                $("#exampleInputEmail1").val(data.email);
                $("#exampleInputMobile1").val(data.mobile_no);
                $("#exampleInputAddress1").val(data.address); // ID corrected here to match HTML
                $("#exampleInputRole1").val(data.designation);
                $("#exampleInputDate1").val(data.joining_date);
            }
        },
        error: function(error) {
            console.error("Error fetching User details:", error);
        }
    });
}



$(document).ready(function() {
    showEmployeeAttendance(id);
});

function showEmployeeAttendance(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '/records/' + id, // Append the employee ID to the URL
        dataType: 'json',
        success: function(data) {
            var d = '';
            var hasCheckedInToday = false;  // To track if the user has checked in today
            var hasCheckedOutToday = false; // To track if the user has checked out today
            var today = new Date().toISOString().split('T')[0]; // Get today's date in YYYY-MM-DD format

            for (var i = 0; i < data.length; i++) {
                console.log("show data", data);

                // Extracting date and time
                var date = new Date(data[i].checkIn);
                var checkInTime = new Date(data[i].checkIn).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
                var checkOutTime = data[i].checkOut ? new Date(data[i].checkOut).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }) : 'N/A';

                // Format the date to YYYY-MM-DD
                var formattedDate = date.toISOString().split('T')[0]; // Extract date part in YYYY-MM-DD format

                // Check if this record is for today
                if (formattedDate === today) {
                    hasCheckedInToday = true;  // User has checked in today
                    if (data[i].checkOut) {
                        hasCheckedOutToday = true;  // User has also checked out today
                    }
                }

                d += '<tr>' +
                    '<td>' + data[i].employee.id + '</td>' +         // Employee ID
                    '<td>' + data[i].employee.fName + '</td>' +      // First Name
                    '<td>' + data[i].employee.lName + '</td>' +      // Last Name
                    '<td>' + formattedDate + '</td>' +               // Date
                    '<td>' + checkInTime + '</td>' +                 // Check In time
                    '<td>' + checkOutTime + '</td>' +                // Check Out time
                    '<td>' + data[i].totalHrs + '</td>' +            // Total Hours Worked
                    '</tr>';
            }

            // Insert the generated rows into the table body
            $('#empAttendance').html(d);

            // Hide the buttons if check-in and check-out are done today
            if (hasCheckedInToday && hasCheckedOutToday) {
                document.getElementById('checkInButton').style.display = 'none';
                document.getElementById('checkOutButton').style.display = 'none';
            } else if (hasCheckedInToday) {
                // If checked in but not yet checked out, show only the Check Out button
                document.getElementById('checkInButton').style.display = 'none';
                document.getElementById('checkOutButton').style.display = 'block';
            } else {
                // If no check-in for today, show only the Check In button
                document.getElementById('checkInButton').style.display = 'block';
                document.getElementById('checkOutButton').style.display = 'none';
            }
        },
        error: function() {
            alert("Error loading data...");
        }
    });
}

/*function showEmployeeAttendance(id) {
    //alert("Table is loading...");
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '/records/' + id, // Append the employee ID to the URL
        dataType: 'json',
        success: function(data) {
            var d = '';

            for (var i = 0; i < data.length; i++) {
                console.log("show data", data);

                // Extracting date and time
                var date = new Date(data[i].checkIn);
                var checkInTime = new Date(data[i].checkIn).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
                var checkOutTime = data[i].checkOut ? new Date(data[i].checkOut).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }) : 'N/A';

                // Format the date to YYYY-MM-DD
                var formattedDate = date.toISOString().split('T')[0]; // Extract date part in YYYY-MM-DD format

                d += '<tr>' +
                    '<td>' + data[i].employee.id + '</td>' +         // Employee ID
                    '<td>' + data[i].employee.fName + '</td>' +      // First Name
                    '<td>' + data[i].employee.lName + '</td>' +      // Last Name
                    '<td>' + formattedDate + '</td>' +               // Date
                    '<td>' + checkInTime + '</td>' +                 // Check In time
                    '<td>' + checkOutTime + '</td>' +                // Check Out time
                    '<td>' + data[i].totalHrs + '</td>' +            // Total Hours Worked
                    '</tr>';
            }

            // Insert the generated rows into the table body
            $('#empAttendance').html(d);
        },
        error: function() {
            alert("Error loading data...");
        }
    });
}
*/

