let roleList = [];

function getAllUsers() {
    $.getJSON('/admin/allUsers', function (data) {
        let rows = '';
        $.each(data, function (key, user) {
            rows += createRows(user);
        });
        $('#tableAllUsers').append(rows);
        $.ajax({
            url: '/admin/authorities',
            method: 'GET',
            dataType: 'json',
            success: function (roles) {
                roleList = roles;
            }
        });
    });
}

getAllUsers();

function createRows(user) {
    let userData = '<tr id=' + user.id + '>';
    userData += '<td>' + user.id + '</td>';
    userData += '<td>' + user.username + '</td>';
    userData += '<td>' + user.lastName + '</td>';
    userData += '<td>' + user.age + '</td>';
    userData += '<td>' + user.email + '</td>';
    userData += '<td>';
    let roles = user.authorities;
    for (let role of roles) {
        userData += role.name.replace('ROLE_', '') + ' ';
    }
    userData += '</td>' +
        '<td>' + '<input id="btnEdit" value="Edit" type="button" ' +
        'class="btn-info btn edit-btn" data-toggle="modal" data-target="#editModal" ' +
        'data-id="' + user.id + '">' + '</td>' +
        '<td>' + '<input id="btnDelete" value="Delete" type="button" class="btn btn-danger del-btn" ' +
        'data-toggle="modal" data-target="#deleteModal" data-id=" ' + user.id + ' ">' + '</td>';
    userData += '</tr>';
    return userData;
}

function getUserRolesForEdit() {
    var allRoles = [];
    $.each($("select[name='editRoles'] option:selected"), function () {
        var role = {};
        role.id = $(this).attr('id');
        role.name = $(this).attr('name');
        allRoles.push(role);
    });
    return allRoles;
}

$(document).on('click', '.edit-btn', function () {
    const userId = $(this).attr('data-id');
    $.ajax({
        url: '/admin/' + userId,
        method: 'GET',
        dataType: 'json',
        success: function (user) {
            $('#editId').val(user.id);
            $('#editName').val(user.username);
            $('#editLastName').val(user.lastName);
            $('#editAge').val(user.age);
            $('#editEmail').val(user.email);
            $('#editPassword').val(user.password);
            $('#editRole').empty();
            roleList.map(role => {
                let flag = user.authorities.find(item => item.id === role.id) ? 'selected' : '';
                $('#editRole').append('<option id="' + role.id + '" ' + flag + ' name="' + role.name + '" >' +
                    role.name.replace('ROLE_', '') + '</option>')
            })
        }
    });
});

$('#editButton').on('click', (e) => {
    e.preventDefault();
    let userEditId = $('#editId').val();
    var editUser = {
        id: $("input[name='id']").val(),
        username: $("input[name='username']").val(),
        lastName: $("input[name='lastName']").val(),
        age: $("input[name='age']").val(),
        email: $("input[name='email']").val(),
        password: $("input[name='password']").val(),
        roles: getUserRolesForEdit()
    }
    $.ajax({
        url: '/admin',
        method: 'PUT',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(editUser),
        success: (data) => {
            let newRow = createRows(data);
            $('#tableAllUsers').find('#' + userEditId).replaceWith(newRow);
            $('#editModal').modal('hide');
            $('#admin-tab').tab('show');
        },
        error: () => {
            console.log("error editUser")
        }
    });
});

$(document).on('click', '.del-btn', function () {
    let userId = $(this).attr('data-id');
    $.ajax({
        url: '/admin/' + userId,
        method: 'GET',
        dataType: 'json',
        success: function (user) {
            $('#delId').empty().val(user.id);
            $('#delName').empty().val(user.username);
            $('#delLastName').empty().val(user.lastName);
            $('#delAge').empty().val(user.age);
            $('#delEmail').empty().val(user.email);
            $('#delPassword').empty().val(user.password);
            $('#delRole').empty();
            roleList.map(role => {
                let flag = user.authorities.find(item => item.id === role.id) ? 'selected' : '';
                $('#delRole').append('<option id="' + role.id + '" ' + flag + ' name="' + role.name + '" >' +
                    role.name.replace('ROLE_', '') + '</option>')
            })
        }
    });
});

$('#deleteButton').on('click', (e) => {
    e.preventDefault();
    let userId = $('#delId').val();
    $.ajax({
        url: '/admin/' + userId,
        method: 'DELETE',
        success: function () {
            $('#' + userId).remove();
            $('#deleteModal').modal('hide');
            $('#admin-tab').tab('show');
        },
        error: () => {
            console.log("error delete user")
        }
    });
});

function getUserRolesForAdd() {
    var allRoles = [];
    $.each($("select[name='addRoles'] option:selected"), function () {
        var role = {};
        role.id = $(this).attr('id');
        role.name = $(this).attr('name');
        allRoles.push(role);
    });
    return allRoles;
}

$('.newUser').on('click', () => {
    $('#name').empty().val('')
    $('#lastName').empty().val('')
    $('#age').empty().val('')
    $('#email').empty().val('')
    $('#password').empty().val('')
    $('#addRole').empty().val('')
    roleList.map(role => {
        $('#addRole').append('<option id="' + role.id + '" name="' + role.name + '">' +
            role.name.replace('ROLE_', '') + '</option>')
    })
})

$("#addNewUserButton").on('click', () => {
    let newUser = {
        username: $('#name').val(),
        lastName: $('#lastName').val(),
        age: $('#age').val(),
        email: $('#email').val(),
        password: $('#password').val(),
        roles: getUserRolesForAdd()
    }
    $.ajax({
        url: '/admin',
        method: 'POST',
        dataType: 'json',
        data: JSON.stringify(newUser),
        contentType: 'application/json; charset=utf-8',
        success: function () {
            $('#tableAllUsers').empty();
            getAllUsers();
            $('#admin-tab').tab('show');
        },
        error: function () {
            alert('error addUser')
        }
    });
});