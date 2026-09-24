# CLAUDE.md

## Project Overview

Biblioteca Backend is a Spring Boot REST API built with Java 25 and Maven.

Follow the existing architecture and conventions before introducing new patterns.

---

## Repository Structure

- controller
- service
- service/impl
- repo
- model
- dto
- config
- exception

---

## Architecture Principles

- Use the existing layered architecture.
- Reuse the generic CRUD abstractions whenever applicable.
- Prefer composition over creating new abstractions.
- Do not duplicate existing functionality.

---

## Coding Conventions

- Use Lombok.
- Prefer constructor injection.
- Use ResponseEntity in controllers.
- Use Jakarta Validation.
- Keep endpoint naming RESTful.
- Follow the existing package structure.

---

## Build

Compile using:

./mvnw -DskipTests compile

Run tests only when explicitly requested.

---

## Agent Catalog

Agents are located in .agents/subagents
Available specialized agents:

- spring-vertical-builder
  Builds or extends CRUD verticals using Libro as the reference implementation.
- spring-api-reviewer
  Reviews backend changes for correctness, persistence, and REST contract issues.

---

## Workflow Catalog

Workflows are located in .agents/workflows

## Features Catalog

Specs are located in .agents/features

---

## General Rules

- Never hardcode secrets.
- Never modify shared infrastructure without an explicit request.
- Follow the project's existing conventions before introducing new ones.
