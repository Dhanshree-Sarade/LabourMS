$(document).ready(function() {
    showEmployeeAttendance();
});

function showEmployeeAttendance() {
	alert("Loading...");
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '/api/attendance/records',
        dataType: 'json',
        success: function(data) {
            var d = '';

            for (var i = 0; i < data.length; i++) {
                console.log("show data", data);

                // Extracting date and time
                var date = new Date(data[i].checkIn); // Use checkIn to format the date
                var checkInTime = new Date(data[i].checkIn).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
                var checkOutTime = data[i].checkOut ? new Date(data[i].checkOut).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }) : 'N/A'; // Handle case when checkOut is null

                // Format the date to YYYY-MM-DD
                var formattedDate = date.toISOString().split('T')[0]; // Extract date part in YYYY-MM-DD format

                // Append the employee attendance data to the table
                d += '<tr>' +
                    '<td>' + data[i].employee.id + '</td>' + // Employee Id
                    '<td>' + data[i].employee.fName + '</td>' + // First Name
                    '<td>' + data[i].employee.lName + '</td>' + // Last Name
                    '<td>' + formattedDate + '</td>' + // Date column
                    '<td>' + checkInTime + '</td>' + // Check In time
                    '<td>' + checkOutTime + '</td>' + // Check Out time
                    '<td>' + data[i].totalHrs + '</td>' + // Total Hours
                    '</tr>';
            }

            $('#empAttendance').html(d); // Insert rows into the table body
        },
        error: function() {
            alert("Error loading data...");
        }
    });
}




/*$(document).ready(function() {
    showEmployeeAttendance();
});

function showEmployeeAttendance() {
    //alert("Table is loading...");
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '/records',
        dataType: 'json',
        success: function(data) {
            var d = '';

            for (var i = 0; i < data.length; i++) {
                console.log("show data", data);

                // Extracting date and time
                var date = new Date(data[i].checkIn); // Use checkIn or checkOut to format the date
                var checkInTime = new Date(data[i].checkIn).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
                var checkOutTime = data[i].checkOut ? new Date(data[i].checkOut).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }) : 'N/A'; // Handle case when checkOut is null

                // Format the date to YYYY-MM-DD
                var formattedDate = date.toISOString().split('T')[0]; // Extract date part in YYYY-MM-DD format

                d += '<tr>' +
                    '<td>' + data[i].id + '</td>' +
                    '<td>' + data[i].fName + '</td>' +
                    '<td>' + data[i].lName + '</td>' +
                    '<td>' + formattedDate + '</td>' + // Date column
                    '<td>' + checkInTime + '</td>' + // Check In time
                    '<td>' + checkOutTime + '</td>' + // Check Out time
                    '<td>' + data[i].totalHrs + '</td>' +
                    '<td>' +
                    '<button type="button" class="btn mb-1 btn-rounded btn-warning gradient-2" data-bs-toggle="modal" data-bs-target="#editEmp" data-table-id="' + data[i].id + '" id="editEmp-' + data[i].id + '"><span class="btn-icon-center"><i class="bi bi-pencil-square"></i></span></button>' +
                    ' <button type="button" class="btn mb-1 btn-danger gradient-3" data-table-id="' + data[i].id + '" id="deleteBtn-' + data[i].id + '"> <span class="btn-icon-center"><i class="bi bi-trash"></i></span></button>' +
                    '</td>' +
                    '</tr>';
            }

            $('#empAttendance').html(d);
        },
        error: function() {
            alert("Error loading data...");
        }
    });
}
*/