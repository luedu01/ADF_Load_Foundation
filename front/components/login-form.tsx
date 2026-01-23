"use client"

import { useState } from "react"
import { useRouter } from "next/navigation"
import { cn } from "@/lib/utils"
import { Button } from "@/components/ui/button"
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import {
  Field,
  FieldError,
  FieldGroup,
  FieldLabel,
} from "@/components/ui/field"
import { Input } from "@/components/ui/input"

export function LoginForm({
  className,
  ...props
}: React.ComponentProps<"div">) {
  const router = useRouter()
  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")
  const [error, setError] = useState<string | null>(null)
  const [success, setSuccess] = useState<string | null>(null)
  const [isSubmitting, setIsSubmitting] = useState(false)

  async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault()
    console.log("Enviando solicitud de autenticación", { username })
    setError(null)
    setSuccess(null)
    setIsSubmitting(true)

    try {
      const response = await fetch("http://localhost:8080/authenticate", {
        method: "POST",
        headers: {
          accept: "*/*",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username,
          password,
        }),
      })

      const data = await response.json().catch(() => null)
      console.log("Respuesta de autenticación", {
        status: response.status,
        ok: response.ok,
        data,
      })

      if (!response.ok) {
        const message =
          (data &&
            typeof data === "object" &&
            "message" in data &&
            (data as { message?: string | null }).message) ||
          "Error al iniciar sesión"

        setError(message || "Error al iniciar sesión")
        return
      }

      if (
        data &&
        typeof data === "object" &&
        "token" in data &&
        typeof (data as { token?: unknown }).token === "string"
      ) {
        const token = (data as { token: string }).token

        if (typeof window !== "undefined") {
          window.localStorage.setItem("authToken", token)
        }
      }

      setSuccess("Inicio de sesión exitoso")
      router.push("/home")
    } catch (error) {
      console.error("Error de red al autenticar", error)
      setError("No se pudo conectar con el servidor")
    } finally {
      setIsSubmitting(false)
    }
  }

  return (
    <div className={cn("flex flex-col gap-6", className)} {...props}>
      <Card>
        <CardHeader>
          <CardTitle>Inicia sesión en tu cuenta</CardTitle>
          <CardDescription>
            Ingresa tu usuario y contraseña para acceder a tu cuenta
          </CardDescription>
        </CardHeader>
        <CardContent>
          <form onSubmit={handleSubmit}>
            <FieldGroup>
              {error && (
                <Field>
                  <FieldError>{error}</FieldError>
                </Field>
              )}
              {success && (
                <Field>
                  <div className="text-sm text-green-600">{success}</div>
                </Field>
              )}
              <Field>
                <FieldLabel htmlFor="username">Usuario</FieldLabel>
                <Input
                  id="username"
                  type="text"
                  placeholder="usuario"
                  required
                  value={username}
                  onChange={(event) => setUsername(event.target.value)}
                />
              </Field>
              <Field>
                <FieldLabel htmlFor="password">Contraseña</FieldLabel>
                <Input
                  id="password"
                  type="password"
                  required
                  value={password}
                  onChange={(event) => setPassword(event.target.value)}
                />
              </Field>
              <Field>
                <Button type="submit" disabled={isSubmitting}>
                  {isSubmitting ? "Iniciando..." : "Iniciar sesión"}
                </Button>
              </Field>
            </FieldGroup>
          </form>
        </CardContent>
      </Card>
    </div>
  )
}
