<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="/css/home-style.css">


        <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        
        <title>Media List</title>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid">
                    <a href="/home" class="navbar-brand">Playlist Share</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#main-navbar" aria-controls="main-navbar" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon">
                    	    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-down-square" viewBox="0 0 16 16">
  								<path d="M3.626 6.832A.5.5 0 0 1 4 6h8a.5.5 0 0 1 .374.832l-4 4.5a.5.5 0 0 1-.748 0l-4-4.5z"/>
  								<path d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm15 0a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1z"/>
							</svg>
						</span>
                    </button>
                    <div class="collapse navbar-collapse" id="main-navbar">
                        <ul class="navbar-nav me-auto">
                            <li class="nav-item">
                                <a href="/friends" class="nav-link">Friends</a>
                            </li>
                            <li class="nav-item">
                                <a href="/lists" class="nav-link">Your Lists</a>
                            </li>
                            <li class="nav-item d-flex">
                                <form action="/logout" method="post" class="align-self-center">
                                    <button type="submit" class="btn text-primary">Logout</button>
                                </form>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class="text-primary p-4">
                <h1>Your List with <c:out value="${friend}"/></h1>
            </div>
        </header>

        <main class="p-5">
            <div class="container bg-dark d-block p-2 w-100 mb-3">
                <ul class="list-group">
                    <c:choose>
                        <c:when test="${currentList.mediaList.size() != 0}">
                            <c:forEach var="media" items="${currentList.mediaList}">
                                <li class="list-group-item"><a href="/media/${media.id}"><c:out value="${media.name}"/></a></li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <li class="list-group-item">Your media list is currently empty!</li>
                        </c:otherwise>
                    </c:choose>
                </ul>

            </div>
            <div class="d-flex justify-content-end w-75"><a href="/lists/${currentList.id}/add" class="btn btn-primary">Create a New Item!</a></div>
        </main>
    </body>
</html>