<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>表单</title>
    <link href="${context}/css/form.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="top_bit">

    <h1></h1>


</div>
<form action="formdemo.php" method="post">
    <label for="name">Name:</label>
    <input type="text" name="name" required placeholder="Name" />

    <label for="email">Email:</label>
    <input type="email" name="email" required placeholder="email@chinaz.com" />

    <label for="website">Website:</label>
    <input type="url" name="website" required placeholder="http://sc.chinaz.com" />

    <label for="number">Number:</label>
    <input type="number" name="number" min="0" max="10" step="2" required placeholder="Even num < 10">

    <label for="range">Range:</label>
    <input type="range" name="range" min="0" max="10" step="2" />

    <label for="message">Message:</label>
    <textarea name="message" required></textarea>

    <input type="submit" value="Send Message" />
</form>

</body>
</html>
