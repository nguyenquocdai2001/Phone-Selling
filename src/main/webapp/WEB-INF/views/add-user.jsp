<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADD USER</title>
</head>
<body>
	<h1>Add User</h1>

	<form action="saveUser" method="POST">
		<br> <label for="name">Name:</label> <input type="text" id="name"
			name="name" required><br>
			
		<br> <label for="email">Email:</label> <input type="email"
			id="email" name="email" required><br>
			
		<br> <label for="password">Password:</label> <input type="text"
			id="password" name="password" required><br>
		
		<br> <label for="phone">Phone:</label> <input type="number"
			id="phone" name="phone" required><br>
			
		<br> <label for="address">Address:</label> <input type="text"
			id="address" name="address" required><br>
			
		<br> <label for="role">Role:</label> <input type="number"
			id="role" name="role" required><br>
			
		<br> <label for="state">State:</label> <input type="text"
			id="state" name="state" required><br>
				
		<br> <input type="submit" value="Save">
	</form>

	<a href="/users">Back to User List</a>

</body>
</html>